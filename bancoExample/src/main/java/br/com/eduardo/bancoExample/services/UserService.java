package br.com.eduardo.bancoExample.services;

import br.com.eduardo.bancoExample.domain.User;
import br.com.eduardo.bancoExample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean saveUser(User user){
        userRepository.save(user);
        return true;
    }

    public Optional<User> searchUserByCpf(String cpf){
        return Optional.ofNullable(userRepository.findByCpf(cpf));
    }

    public Optional<User> searchUserById(String id){
        return userRepository.findById(Long.getLong(id));
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }
}
