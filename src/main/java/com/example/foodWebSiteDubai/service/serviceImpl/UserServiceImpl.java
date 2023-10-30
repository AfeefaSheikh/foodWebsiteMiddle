package com.example.foodWebSiteDubai.service.serviceImpl;

import com.example.foodWebSiteDubai.dto.request.UserRequest;
import com.example.foodWebSiteDubai.entity.MailLinkDetails;
import com.example.foodWebSiteDubai.entity.UserMaster;
import com.example.foodWebSiteDubai.exception.CommonException;
import com.example.foodWebSiteDubai.repository.MailLinkDetailsRepo;
import com.example.foodWebSiteDubai.repository.UserMasterRepo;
import com.example.foodWebSiteDubai.service.UserService;
import com.example.foodWebSiteDubai.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMasterRepo userMasterRepo;
    @Autowired
    private MailLinkDetailsRepo mailLinkDetailsRepo;
    @Autowired
    EmailUtil emailUtil;
    @Override
    @Transactional
    public String createUser(UserRequest userRequest) throws MessagingException {
        Optional<UserMaster> emailId=userMasterRepo.findByEmailId(userRequest.getEmailId());
        if(emailId.isPresent()){
            throw  new CommonException(HttpStatus.BAD_REQUEST,"EmailId Already Exists");
        }
        UserMaster userMaster=new UserMaster();
        userMaster.setEmailId(userRequest.getEmailId());
        userMaster.setStatus("INACTIVE");
        userMaster.setPassword(userRequest.getPassword());
        userMaster.setFirstName(userRequest.getFirstName());
        userMaster.setLastName(userRequest.getLastName());
        UserMaster user=userMasterRepo.save(userMaster);
        MailLinkDetails mailLinkDetails=new MailLinkDetails();
        mailLinkDetails.setLinkType("ACTIVATION_ACCOUNT");
        mailLinkDetails.setUserId(user.getUserId());
        UUID uuid=UUID.randomUUID();
        mailLinkDetails.setUuid(uuid.toString());
     MailLinkDetails mailLinkDetails1=mailLinkDetailsRepo.save(mailLinkDetails);
        emailUtil.sendMessage(user.getEmailId(), mailLinkDetails1.getUuid(),user.getUserId());
        return null;
    }

    @Override
    public String activateUser(String uuid, Long userId) {
        Optional<MailLinkDetails> mailLinkDetails=mailLinkDetailsRepo.findByUserIdAndUuid(userId,uuid);
        if(mailLinkDetails.isPresent()) {
            Optional<UserMaster> userMaster = Optional.ofNullable(userMasterRepo.findById(userId).orElseThrow(() -> {
                throw new CommonException(HttpStatus.BAD_REQUEST, "Id Not Present");
            }));
           if(userMaster.isPresent()){
              UserMaster user1 =userMaster.get();
              user1.setStatus("ACTIVE");
               userMasterRepo.save(user1);
           }
        }

        return null;
    }
}
