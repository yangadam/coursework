package cn.edu.xmu.comm;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.repository.CommunityRepository;
import cn.edu.xmu.comm.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = {DataEntity.class, Community.class})
@EnableJpaRepositories(basePackageClasses = {CommunityRepository.class})
public class NextGenCommApplication {
    public static void main(String[] args) {
        SpringApplication.run(NextGenCommApplication.class, args);
    }
}
