package gov.nysed.dockertutorial;

import gov.nysed.dockertutorial.enums.ProjectRole;
import gov.nysed.dockertutorial.model.Project;
import gov.nysed.dockertutorial.model.User;
import gov.nysed.dockertutorial.repository.ProjectMembershipRepository;
import gov.nysed.dockertutorial.repository.ProjectRepository;
import gov.nysed.dockertutorial.repository.UserRepository;
import gov.nysed.dockertutorial.service.ProjectMembershipService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  private final ProjectMembershipRepository projectMembershipRepository;
  private final ProjectRepository projectRepository;
  private final UserRepository userRepository;
  private final ProjectMembershipService projectMembershipService;

  public DataInitializer(ProjectMembershipRepository projectMembershipRepository,
      ProjectRepository projectRepository, UserRepository userRepository,
      ProjectMembershipService projectMembershipService) {
    this.projectMembershipRepository = projectMembershipRepository;
    this.projectRepository = projectRepository;
    this.userRepository = userRepository;
    this.projectMembershipService = projectMembershipService;
  }

  @Override
  public void run(String... args) throws Exception {
    if (userRepository.count() == 0) {
      userRepository.save(new User("Alice Avery"));
      userRepository.save(new User("Bill Billingsworth"));
      userRepository.save(new User("Charlie Chaplin"));
      userRepository.save(new User("David Davidson"));
      userRepository.save(new User("Emma Emerson"));
      userRepository.save(new User("Frank Franklin"));
      userRepository.save(new User("Grace Garrison"));
      userRepository.save(new User("Henry Henderson"));
      userRepository.save(new User("Ian Iverson"));
      userRepository.save(new User("Jack Jackson"));
      userRepository.save(new User("Kevin Kevinson"));
      userRepository.save(new User("Liam Liamson"));
      userRepository.save(new User("Michael Michaelson"));
      userRepository.save(new User("Nathan Nathanson"));
      userRepository.save(new User("Oliver Oliverson"));
      userRepository.save(new User("Peter Peterson"));
      userRepository.save(new User("Quincy Quincyson"));
      userRepository.save(new User("Robert Robertson"));
      userRepository.save(new User("Samuel Samuelson"));
      userRepository.save(new User("Thomas Thomason"));
      userRepository.save(new User("Upton Uptonon"));
      userRepository.save(new User("Victor Victorson"));
      userRepository.save(new User("William Williamson"));
      userRepository.save(new User("Xavier Xavierson"));
      userRepository.save(new User("Yusuf Yusufson"));
      userRepository.save(new User("Zachary Zacharison"));
      userRepository.save(new User("Michael Panzer"));
      userRepository.save(new User("Stefanie Husak"));
      userRepository.save(new User("William Lesniak"));
      System.out.println("Users seeded");
    }
    if (projectRepository.count() == 0) {
      projectRepository.save(new Project("Exam Request System"));
      projectRepository.save(new Project("Shipping Notice Generator"));
      projectRepository.save(new Project("Library Development Grants"));
      projectRepository.save(new Project("Fire Code Planning"));
      System.out.println("Projects seeded");
    }
    if (projectMembershipRepository.count() == 0) {
      projectMembershipService.assignUserToProject("Michael Panzer", "Fire Code Planning", ProjectRole.ADMIN);
      projectMembershipService.assignUserToProject("Stefanie Husak", "Exam Request System", ProjectRole.ADMIN);
      projectMembershipService.assignUserToProject("William Lesniak", "Exam Request System", ProjectRole.DEVELOPER);
      projectMembershipService.assignUserToProject("William Lesniak", "Fire Code Planning", ProjectRole.DEVELOPER);
      System.out.println("ProjectMembership seeded");
    }

  }
}