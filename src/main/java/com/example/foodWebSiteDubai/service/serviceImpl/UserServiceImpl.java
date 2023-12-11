package com.example.foodWebSiteDubai.service.serviceImpl;

import com.example.foodWebSiteDubai.dto.request.ResetDto;
import com.example.foodWebSiteDubai.dto.request.UserRequest;
import com.example.foodWebSiteDubai.entity.MailLinkDetails;
import com.example.foodWebSiteDubai.entity.UserMaster;
import com.example.foodWebSiteDubai.entity.UserRole;
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
import java.time.LocalDateTime;
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


//    @Autowired
//    private JwtUserDetailsService jwtUserDetailsService;
//    @Autowired
//    private JwtHelper jwtHelper;
//
//    @Autowired
//    private AuthenticationManager manager;
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    @Transactional
    public String createUser(UserRequest userRequest) throws MessagingException {
        Optional<UserMaster> emailId=userMasterRepo.findByEmailIdAndUserName(userRequest.getEmailId(),userRequest.getUserName());
        if(emailId.isPresent()){
            throw  new CommonException(HttpStatus.BAD_REQUEST,"EmailId Already Exists");
        }
        UserMaster userMaster=new UserMaster();
        userMaster.setEmailId(userRequest.getEmailId());
        userMaster.setStatus("INACTIVE");
        userMaster.setPassword(userRequest.getPassword());
        userMaster.setFirstName(userRequest.getFirstName());
        userMaster.setLastName(userRequest.getLastName());
        userMaster.setUserName(userRequest.getUserName());
//        UserRole userRoleEntity = new UserRole();
//        userRoleEntity.setRoleName(userRequest.getRoleName());
//        userMaster.setUserRoleEntity(userRoleEntity);
        UserMaster user=userMasterRepo.save(userMaster);
//        MailLinkDetails mailLinkDetails=new MailLinkDetails();
//        mailLinkDetails.setUserId(user.getUserId());
//        UUID uuid=UUID.randomUUID();
//        mailLinkDetails.setUuid(uuid.toString());
//        LocalDateTime localDateTime = LocalDateTime.now();
//        localDateTime.plusHours(1);
//        mailLinkDetails.setExpirationTime(localDateTime);
//        mailLinkDetails.setMailLinkId(1l);
//        Long linkId = 1l;
//        MailLinkDetails mailLinkDetails1=mailLinkDetailsRepo.save(mailLinkDetails);
//        emailUtil.sendMessage(user.getEmailId(), mailLinkDetails1.getUuid(),user.getUserId(), linkId );
        return null;
    }


    @Override
    public String activateUser(String uuid, Long userId) {
        Optional<MailLinkDetails> mailLinkDetails=mailLinkDetailsRepo.findByUserIdAndUuid(userId,uuid);
        if(mailLinkDetails.isPresent()) {
            MailLinkDetails mailLinkDetails1 = mailLinkDetails.get();
            LocalDateTime localDateTime =LocalDateTime.now();
            if(localDateTime.isBefore(mailLinkDetails1.getExpirationTime())){
                throw new CommonException(HttpStatus.BAD_REQUEST, "Time expired");
            }
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

    @Override
    public String forgotPassword(ResetDto resetDto) throws MessagingException {
        Optional<UserMaster> email = userMasterRepo.findByEmailId(resetDto.getEmail());
        if(email.isPresent()){
            MailLinkDetails mailLinkDetails=new MailLinkDetails();
//            mailLinkDetails.setLinkType("RESET PASSWORD");
            mailLinkDetails.setUserId(email.get().getUserId());
            UUID uuid=UUID.randomUUID();
            mailLinkDetails.setUuid(uuid.toString());
            LocalDateTime localDateTime = LocalDateTime.now();
            localDateTime.plusHours(1);
            mailLinkDetails.setExpirationTime(localDateTime);
            mailLinkDetails.setMailLinkId(3l);
            Long linkId = 3l;
            MailLinkDetails mailLinkDetails1=mailLinkDetailsRepo.save(mailLinkDetails);
            emailUtil.sendMessage(resetDto.getEmail(),mailLinkDetails1.getUuid(),email.get().getUserId(),linkId);
            return null;
        }
        return null;
    }
    //http://localhost:9731/v1/user/change?token=uuid +token
    @Override
    public boolean changePassword(String token, String newPassword, String confirmPassword) {
        Optional <MailLinkDetails> mailLinkDetails = mailLinkDetailsRepo.findByUuid(token);
        if(mailLinkDetails.isPresent()){
            MailLinkDetails mailLinkDetails1 = mailLinkDetails.get();
//            Optional<UserMaster> userMaster = userMasterRepo.findById(mailLinkDetails1.getUserId());
            LocalDateTime localDateTime =LocalDateTime.now();
            if(localDateTime.isBefore(mailLinkDetails1.getExpirationTime())){
                throw new CommonException(HttpStatus.BAD_REQUEST, "Time expired");
            }
            else {
                if(newPassword.equals(confirmPassword)){
                    Optional<UserMaster> userMaster = userMasterRepo.findById(mailLinkDetails1.getUserId());
                    UserMaster userMaster1 = userMaster.get();
                    userMaster1.setPassword(newPassword);
                    userMasterRepo.save(userMaster1);
                }
                else{
                    throw new CommonException(HttpStatus.BAD_REQUEST, "Password Doesn't Match");
                }
            }
        }
        return false;
    }

//    @Override
//    public JwtTokenResponse generateAuthToken(AuthDto authDto) {
//        if (authDto.getEmail().isEmpty() || authDto.getPassword().isEmpty()) {
//            throw new CommonException(HttpStatus.BAD_REQUEST, USERNAME_PASSWORD_EMPTY_MSG);
//        }
////        UserMaster userMaster = userMasterRepo.findByEmailId(authDto.getEmail());
//        if (userMaster != null) {
//            JwtTokenResponse jwtTokenResponse = new JwtTokenResponse();
//            doAuthenticate(authDto.getEmail(), authDto.getPassword());
//            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authDto.getEmail());
//            String as= userDetails.getAuthorities().toString();
//            final String token = jwtHelper.generateToken(userDetails);
//            jwtTokenResponse.setEmailId(authDto.getEmail());
//            jwtTokenResponse.setAccessToken(token);
//            return jwtTokenResponse;
//        } else {
//            throw new CommonException(HttpStatus.BAD_REQUEST, ACCESS_DENIED);
//        }
//    }


}
