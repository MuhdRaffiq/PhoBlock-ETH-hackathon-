package com.hackathon.phoblock.Controller;

import com.hackathon.phoblock.Exceptions.RegistrationFailedException;
import com.hackathon.phoblock.Exceptions.ResourceNotFoundException;
import com.hackathon.phoblock.Model.PhoBlockLoginAuthentication;
import com.hackathon.phoblock.Model.PhoBlockUser;
import com.hackathon.phoblock.Repository.PhoBlockUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PhoBlockUserController {
    @Autowired
    PhoBlockUserRepository phoBlockUserRepository;

    @PostMapping("/User")
    PhoBlockUser createUser(@RequestBody PhoBlockUser phoBlockUser) throws RegistrationFailedException {
        PhoBlockUser retrievedUser = phoBlockUserRepository.findByUserName(phoBlockUser.getUserName());

        if(retrievedUser == null){
            throw new RegistrationFailedException("Username has been taken. Please choose another username");
        }else{
            phoBlockUserRepository.save(phoBlockUser);

            return phoBlockUser;
        }
    }

    @GetMapping("/Users")
    List<PhoBlockUser> getAllUsers(){
        List<PhoBlockUser> getAllUsers = phoBlockUserRepository.findAll();

        return getAllUsers;
    }

    @GetMapping("/User/{username}")
    PhoBlockUser getUser(@PathVariable String username) throws ResourceNotFoundException {
        PhoBlockUser retrievedUser = phoBlockUserRepository.findByUserName(username);

        if(retrievedUser == null){
            throw new ResourceNotFoundException("Username: " + username + " is not found");
        }else{
            return retrievedUser;
        }
    }

    @PostMapping("/AuthenticateLogin")
    PhoBlockUser validateLogin(@RequestBody PhoBlockLoginAuthentication phoBlockLoginAuthentication)
            throws ResourceNotFoundException {
        PhoBlockUser retrievedUser = phoBlockUserRepository.findByUserName(phoBlockLoginAuthentication.getUsername());

        if(retrievedUser == null){
            throw new ResourceNotFoundException("Invalid Username/Password");
        }else{
            if(!retrievedUser.getUserPassword().equals(phoBlockLoginAuthentication.getPassword())){
                throw new ResourceNotFoundException("Invalid Username/Password");
            }else{
                PhoBlockUser phoBlockUser = new PhoBlockUser();

                phoBlockUser.setId(retrievedUser.getId());
                phoBlockUser.setFirstName(retrievedUser.getFirstName());
                phoBlockUser.setLastName(retrievedUser.getLastName());
                phoBlockUser.setEmailAddress(retrievedUser.getEmailAddress());
                phoBlockUser.setUserName(retrievedUser.getUserName());
                phoBlockUser.setUserPassword(null);
                phoBlockUser.setDateCreated(retrievedUser.getDateCreated());

                return phoBlockUser;
            }
        }
    }

    @PutMapping("/User/{username}")
    public PhoBlockUser updateUser(@RequestBody PhoBlockUser phoBlockUser, @PathVariable String username) throws ResourceNotFoundException {
        PhoBlockUser retrievedUser = phoBlockUserRepository.findByUserName(username);

        if(retrievedUser == null){
            throw new ResourceNotFoundException("Username: " + username + " is not found");
        }else{
            retrievedUser.setFirstName(phoBlockUser.getFirstName());
            retrievedUser.setLastName(phoBlockUser.getLastName());
            retrievedUser.setEmailAddress(phoBlockUser.getEmailAddress());
            retrievedUser.setUserName(phoBlockUser.getUserName());
            retrievedUser.setUserPassword(phoBlockUser.getUserPassword());

            phoBlockUserRepository.save(retrievedUser);

            return retrievedUser;
        }
    }
}