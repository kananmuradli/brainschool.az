package az.brainschool.service.abstracts;

import az.brainschool.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrainSchoolService {

    void save(User user);

}
