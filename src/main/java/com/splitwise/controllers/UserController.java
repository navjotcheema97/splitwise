package com.splitwise.controllers;

import com.splitwise.dtos.UserDTO;
import com.splitwise.exceptions.authentication.PasswordDoesNotMatchException;
import com.splitwise.exceptions.validations.DuplicateUsernameException;
import com.splitwise.models.Expense;
import com.splitwise.models.Group;
import com.splitwise.models.User;
import com.splitwise.repositories.interfaces.UserRepository;
import com.splitwise.services.authentication.AuthenticationContext;
import com.splitwise.services.authentication.PasswordEncoder;
import com.splitwise.services.settle.user.SettleUserStrategy;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class UserController {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final SettleUserStrategy settleUserStrategy;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, SettleUserStrategy settleUserStrategy) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.settleUserStrategy = settleUserStrategy;
    }

    /**
     * This method requires the passwordEncoder to be set
     */
    public User register(UserDTO details) {
        User user = new User();

        if (userRepository.findUserByUsername(details.username).isPresent()) {
            throw new DuplicateUsernameException("Username already exists!");
        }

        user.setUsername(details.username);
        user.setPhoneNumber(details.phoneNumber);
        user.setFullname(details.fullname);
        String saltedHashedPassword = passwordEncoder.encode(details.password, details.username);
        user.setHashedSaltedPassword(saltedHashedPassword);

        userRepository.save(user);
        return user;
    }

    public void changePassword(AuthenticationContext authenticationContext,
                               String oldPassword, String newPassword) {
        User user = authenticationContext.getCurrentlyLoggedInUserOrElseThrow();
        if (!user.getHashedSaltedPassword().equals(passwordEncoder.encode(oldPassword, user.getUsername()))) {
            throw new PasswordDoesNotMatchException("");
        }
        user.setHashedSaltedPassword(passwordEncoder.encode(newPassword, user.getUsername()));
        userRepository.save(user);
    }

    public void updateProfile(AuthenticationContext authenticationContext,
                              UserDTO newDetails) {
        User user = authenticationContext.getCurrentlyLoggedInUserOrElseThrow();
        if (!user.getHashedSaltedPassword().equals(passwordEncoder.encode(user.getHashedSaltedPassword(), user.getUsername()))) {
            throw new PasswordDoesNotMatchException("");
        }
        user.setFullname(newDetails.fullname);
        user.setPhoneNumber(newDetails.phoneNumber);
        userRepository.save(user);
    }

    public Double getMyTotalOwed(AuthenticationContext authenticationContext) {
        User user = authenticationContext.getCurrentlyLoggedInUserOrElseThrow();
        return user.getTotalOwed();
    }

    public Set<Expense> getMyExpenseHistory(AuthenticationContext authenticationContext) {
        User user = authenticationContext.getCurrentlyLoggedInUserOrElseThrow();
        return user.getExpenses();
    }

    public Set<Group> getMyGroups(AuthenticationContext authenticationContext) {
        User user = authenticationContext.getCurrentlyLoggedInUserOrElseThrow();
        return user.getGroups();
    }

    public String settleUp(AuthenticationContext authenticationContext) {
        User user = authenticationContext.getCurrentlyLoggedInUserOrElseThrow();
        return settleUserStrategy.settle(user);
    }
}
