package ru.kazimir.bortnik.controller_module.configs.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kazimir.bortnik.repository_module.HotelRepository;
import ru.kazimir.bortnik.repository_module.RoleRepository;
import ru.kazimir.bortnik.repository_module.UserRepository;
import ru.kazimir.bortnik.repository_module.model.Hotel;
import ru.kazimir.bortnik.repository_module.model.Role;
import ru.kazimir.bortnik.repository_module.model.User;
import ru.kazimir.bortnik.repository_module.model.WorkingHours;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.kazimir.bortnik.controller_module.constant.RoleConstants.ADMIN_ROLE_NAME;
import static ru.kazimir.bortnik.controller_module.constant.RoleConstants.USER_ROLE_NAME;

@Component
public class MongoDBConfiguration implements CommandLineRunner {
    private final RoleRepository roleRepositoryImpl;
    private final UserRepository userRepositoryImpl;
    private final HotelRepository hotelRepository;
    private static final String DEFAULT_OPENING_TIME = "09:00";
    private static final String DEFAULT_CLOSING_TIME = "23:00";

    public MongoDBConfiguration(RoleRepository roleRepositoryImpl,
                                UserRepository userRepositoryImpl,
                                HotelRepository hotelRepository) {
        this.roleRepositoryImpl = roleRepositoryImpl;
        this.userRepositoryImpl = userRepositoryImpl;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        roleInitialization();
        userInitialization();
        HotelInitialization();
    }

    private void roleInitialization() {
        List<Role> roleList = Arrays.asList(new Role(ADMIN_ROLE_NAME), new Role(USER_ROLE_NAME));
        roleRepositoryImpl.deleteAll();
        roleRepositoryImpl.insert(roleList);
    }

    private void userInitialization() {
        Role roleUser = roleRepositoryImpl.getByName(USER_ROLE_NAME);
        Role roleAdmin = roleRepositoryImpl.getByName(ADMIN_ROLE_NAME);

        List<User> userList = Arrays.asList(
                new User("User",
                        "SurnameUser",
                        "user@mail.ru",
                        "$2a$12$8bfPX.6k0VKckMpqm24al.w5qO5dSg3oiXR262D8.nogEcmnJ83Dy",
                        roleUser
                ),
                new User("Admin",
                        "SurnameAdmin",
                        "Admin@mail.ru",
                        "$2a$12$oGKWj2pwGQ8TxuEVkvX/6O2kzGwVWzxbCy090KBsjI53l/YLKN1sW",
                        roleAdmin
                )
        );
        userRepositoryImpl.deleteAll();
        userRepositoryImpl.insert(userList);
    }

    private void HotelInitialization() {
        Hotel hotel = new Hotel();
        Map<DayOfWeek, WorkingHours> scheduleWorking = new HashMap<>();
        Arrays.stream(DayOfWeek.values()).forEach(dayOfWeek -> scheduleWorking.put(dayOfWeek,
                new WorkingHours(LocalTime.parse(DEFAULT_OPENING_TIME), LocalTime.parse(DEFAULT_CLOSING_TIME))));
        hotel.setScheduleWorking(scheduleWorking);
        hotelRepository.deleteAll();
        hotelRepository.insert(hotel);
    }
}
