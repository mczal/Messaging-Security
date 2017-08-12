package com.test.dana.controller.rest;

import com.test.dana.config.model.JwtUser;
import com.test.dana.dto.base.rest.BaseRestResponse;
import com.test.dana.dto.base.rest.HttpCode;
import com.test.dana.dto.base.rest.RestException;
import com.test.dana.dto.base.rest.RestListResponse;
import com.test.dana.dto.message.MessageType;
import com.test.dana.dto.message.request.ReadMessageDtoRequest;
import com.test.dana.dto.message.request.SendMessageDtoRequest;
import com.test.dana.dto.message.response.MessageInDtoResponse;
import com.test.dana.dto.message.response.MessageOutDtoResponse;
import com.test.dana.model.Message;
import com.test.dana.model.User;
import com.test.dana.service.MessageService;
import com.test.dana.service.UserService;
import com.test.dana.utils.converter.MessageInConverter;
import com.test.dana.utils.converter.MessageOutConverter;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mczal on 8/12/17.
 */
@RestController
@RequestMapping(UserController.ABSOLUTE_PATH)
public class UserController {

  public static final String ABSOLUTE_PATH = "/api/v1/auth";

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private MessageInConverter messageInConverter;

  @Autowired
  private MessageOutConverter messageOutConverter;

  @Autowired
  private MessageService messageService;

  @Autowired
  private SimpMessagingTemplate template;

  @Autowired
  private UserService userService;

  private Authentication getAuth() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  private User getAuthenticatedUser(String requestId) throws Exception {
    JwtUser jwtUser = (JwtUser) getAuth().getPrincipal();
    logger.debug("JwtUser : \n" + jwtUser.toString() + "\n");
    return userService.findById(requestId, jwtUser.getId());
  }

  @PostMapping(value = "/block-user/{user}",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseRestResponse blockUser(@RequestParam String requestId, @PathVariable(name = "user")
  @ApiParam(value = "username or email",
      required = true) String usernameOrEmail) throws Exception {

    JwtUser jwtUser = (JwtUser) getAuth().getPrincipal();
    userService.blockUser(requestId, jwtUser.getId(), usernameOrEmail);

    return new BaseRestResponse(requestId, HttpCode.OK);
  }

  @PostMapping(value = "/delete-account")
  public BaseRestResponse deleteAccount(@RequestParam String requestId) throws Exception {
    User user = getAuthenticatedUser(requestId);
    user.setMarkForDelete(true);
    /**
     * Freeing username for other prospective user
     * */
    user.setUsername(null);
    userService.update(requestId, user);

    return new BaseRestResponse(requestId, HttpCode.OK);
  }

  @GetMapping(value = "/get-in-messages",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RestListResponse<MessageInDtoResponse> getAllInMessages(@RequestParam String requestId,
      @RequestParam Integer page, @RequestParam Integer size, @RequestParam MessageType messageType)
      throws Exception {

    User user = getAuthenticatedUser(requestId);

    PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "createdDate");
    Page<Message> messages = null;
    switch (messageType) {
      case UNREAD:
        messages = messageService.findByReceiverAndIsRead(user, false, pageRequest);
        break;
      case READ:
        messages = messageService.findByReceiverAndIsRead(user, true, pageRequest);
        break;
      case ALL:
        messages = messageService.findByReceiver(user, pageRequest);
        break;
      default:
        throw new RestException(requestId, HttpCode.BAD_REQUEST,
            "Undefined message type"); //never happen
    }

    logger.debug("Messages : \n" + messages.getContent().toString() + "\n");

    Page<MessageInDtoResponse> messageInDtoResponses = messages.map(this.messageInConverter);

    return new RestListResponse<MessageInDtoResponse>(requestId, HttpCode.OK,
        messageInDtoResponses);
  }

  @GetMapping(value = "/get-out-messages",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RestListResponse<MessageOutDtoResponse> getAllOutMessages(@RequestParam String requestId,
      @RequestParam Integer page, @RequestParam Integer size) throws Exception {

    User user = getAuthenticatedUser(requestId);

    Page<Message> messages = messageService
        .findBySender(user, new PageRequest(page, size, Sort.Direction.DESC, "createdDate"));
    Page<MessageOutDtoResponse> messageOutDtoResponses = messages.map(this.messageOutConverter);

    return new RestListResponse<MessageOutDtoResponse>(requestId, HttpCode.OK,
        messageOutDtoResponses);

  }

  @GetMapping(value = "/get-in-messages/{user}")
  public RestListResponse<MessageInDtoResponse> getInMessagesFromUser(
      @RequestParam String requestId, @RequestParam Integer page, @RequestParam Integer size,
      @RequestParam MessageType messageType, @PathVariable(name = "user")
  @ApiParam(value = "username or email",
      required = true) String usernameOrEmail) throws Exception {

    User receiver = getAuthenticatedUser(requestId);
    User sender = userService.findByUsernameOrEmail(requestId, usernameOrEmail);

    PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.DESC, "createdDate");
    Page<Message> messages = null;
    switch (messageType) {
      case UNREAD:
        messages = messageService
            .findBySenderAndReceiverIsReadAndIsBlockedFalse(sender, receiver, false, pageRequest);
        break;
      case READ:
        messages = messageService
            .findBySenderAndReceiverIsReadAndIsBlockedFalse(sender, receiver, true, pageRequest);
        break;
      case ALL:
        messages =
            messageService.findBySenderAndReceiverAndIsBlockedFalse(sender, receiver, pageRequest);
        break;
      default:
        throw new RestException(requestId, HttpCode.BAD_REQUEST,
            "Undefined message type"); //never happen
    }

    logger.debug("Messages : \n" + messages.getContent().toString() + "\n");

    Page<MessageInDtoResponse> messageInDtoResponses = messages.map(this.messageInConverter);

    return new RestListResponse<MessageInDtoResponse>(requestId, HttpCode.OK,
        messageInDtoResponses);
  }

  @GetMapping(value = "/get-out-messages/{user}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public RestListResponse<MessageOutDtoResponse> getOutMessagesFromUser(
      @RequestParam String requestId, @RequestParam Integer page, @RequestParam Integer size,
      @PathVariable(name = "user") @ApiParam(value = "username or email",
          required = true) String usernameOrEmail) throws Exception {
    User sender = getAuthenticatedUser(requestId);
    User receiver = userService.findByUsernameOrEmail(requestId, usernameOrEmail);

    Page<Message> messages = messageService.findBySenderAndReceiver(sender, receiver,
        new PageRequest(page, size, Sort.Direction.DESC, "createdDate"));
    Page<MessageOutDtoResponse> messageOutDtoResponses = messages.map(this.messageOutConverter);

    return new RestListResponse<MessageOutDtoResponse>(requestId, HttpCode.OK,
        messageOutDtoResponses);
  }

  @PostMapping(value = "/read-messages")
  public BaseRestResponse readMessage(@RequestParam String requestId,
      @Valid @RequestBody ReadMessageDtoRequest content, BindingResult bindingResult)
      throws Exception {
    if (bindingResult.hasErrors())
      throw new RestException(requestId, HttpCode.VALIDATION_CONSTRAINS, bindingResult);

    messageService.readMessages(requestId, content.getIds());

    return new BaseRestResponse(requestId, HttpCode.OK);
  }

  //  @MessageMapping("/hello")
  //  @SendTo("/topic/greetings")
  //  public Greeting greeting(HelloMessage message) throws Exception {
  //    Thread.sleep(1000); // simulated delay
  //    return new Greeting("Hello, " + message.getName() + "!");
  //  }

  @PostMapping(value = "/send-message",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public BaseRestResponse sendMessage(@RequestParam String requestId,
      @Valid @RequestBody SendMessageDtoRequest content, BindingResult bindingResult)
      throws Exception {
    if (bindingResult.hasErrors()) {
      throw new RestException(requestId, HttpCode.VALIDATION_CONSTRAINS, bindingResult);
    }

    User sender = getAuthenticatedUser(requestId);
    User receiver = userService.findByUsernameOrEmailWithBlockedUsersDetail(requestId,
        content.getRecipientEmailOrUsername());

    Message message = new Message();
    message.setRead(false);
    message.setMessage(content.getMessage());
    message.setReceiver(receiver);
    message.setSender(sender);

    /**
     * Message will not be sent if receiver has already blocked sender
     * */
    if (receiver.getBlockedUsers().stream()
        .anyMatch(blockedUser -> blockedUser.getBlocked().getId().equals(sender.getId()))) {
      logger.debug("\nreceiver has blocked the sender\n");
      message.setBlocked(true);
    }

    messageService.save(message);

    /**
     * Send payload to topic for specific user
     * */
    template.convertAndSend("/topic/messaging/" + receiver.getUsername(),
        this.messageInConverter.convert(message));

    return new BaseRestResponse(requestId, HttpCode.OK);

  }


}
