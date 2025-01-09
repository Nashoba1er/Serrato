package com.emse.spring.serrato.api;

import com.emse.spring.serrato.dao.UserDao;
import com.emse.spring.serrato.mapper.UserMapper;
import com.emse.spring.serrato.model.UserEntity;
import com.emse.spring.serrato.record.User;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Transactional
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Récupérer la liste de tous les utilisateurs.
     */
    @GetMapping
    public List<User> findAll() {
        return userDao.findAll()
                .stream()
                .map(UserMapper::toRecord)
                .sorted(Comparator.comparing(User::username))
                .collect(Collectors.toList());
    }

    /**
     * Récupérer un utilisateur particulier par son identifiant.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        return userDao.findById(id)
                .map(user -> ResponseEntity.ok(UserMapper.toRecord(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Créer un nouvel utilisateur.
     */
    @PostMapping
    public ResponseEntity<User> create(@RequestBody UserCommand userCommand) {
        UserEntity entity = new UserEntity(userCommand.username(), userCommand.password());
        UserEntity savedEntity = userDao.save(entity);
        return ResponseEntity.ok(UserMapper.toRecord(savedEntity));
    }

    /**
     * Mettre à jour un utilisateur existant par son ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserCommand userCommand) {
        UserEntity entity = userDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        // Mise à jour des champs
        entity.setUsername(userCommand.username());
        entity.setPassword(userCommand.password());

        // Sauvegarde de l'entité
        userDao.save(entity);
        return ResponseEntity.ok(UserMapper.toRecord(entity));
    }


    /**
     * Supprimer un capteur par son ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            if (!userDao.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            userDao.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
