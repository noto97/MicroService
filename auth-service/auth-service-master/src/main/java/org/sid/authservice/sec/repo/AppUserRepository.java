package org.sid.authservice.sec.repo;

import org.sid.authservice.sec.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.java2d.pipe.AlphaPaintPipe;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
