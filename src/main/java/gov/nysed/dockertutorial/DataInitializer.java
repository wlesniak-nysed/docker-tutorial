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
      userRepository.save(new User("aavery1","Alice","Avery"));
      userRepository.save(new User("aavery2","Alison","Avery"));
      userRepository.save(new User("aavery11","Alex","Avery"));
      userRepository.save(new User("bbillingswo1","Bill","Billingsworth"));
      userRepository.save(new User("cchaplin1","Charlie","Chaplin"));
      userRepository.save(new User("ddavidson1","David","Davidson"));
      userRepository.save(new User("eemerson1","Emma","Emerson"));
      userRepository.save(new User("ffranklin1","Frank","Franklin"));
      userRepository.save(new User("ggarrison1","Grace","Garrison"));
      userRepository.save(new User("hhenderson1","Henry","Henderson"));
      userRepository.save(new User("iiverson1","Ian","Iverson"));
      userRepository.save(new User("jjackson1","Jack","Jackson"));
      userRepository.save(new User("kkevinson1","Kevin","Kevinson"));
      userRepository.save(new User("lliamson1","Liam","Liamson"));
      userRepository.save(new User("mmichaelson1","Michael","Michaelson"));
      userRepository.save(new User("nnathanson1","Nathan","Nathanson"));
      userRepository.save(new User("ooliverson1","Oliver","Oliverson"));
      userRepository.save(new User("ppeterson1","Peter","Peterson"));
      userRepository.save(new User("qquincyson1","Quincy","Quincyson"));
      userRepository.save(new User("rrobertson1","Robert","Robertson"));
      userRepository.save(new User("ssamuelson1","Samuel","Samuelson"));
      userRepository.save(new User("tthomason1","Thomas","Thomason"));
      userRepository.save(new User("uuptonon1","Upton","Uptonon"));
      userRepository.save(new User("vvictorson1","Victor","Victorson"));
      userRepository.save(new User("wwilliamson1","William","Williamson"));
      userRepository.save(new User("xxavierson1","Xavier","Xavierson"));
      userRepository.save(new User("yyusufson1","Yusuf","Yusufson"));
      userRepository.save(new User("zzacharison1","Zachary","Zacharison"));
      userRepository.save(new User("mpanzer1","Michael","Panzer"));
      userRepository.save(new User("shusak1","Stefanie","Husak"));
      userRepository.save(new User("wlesniak1","William","Lesniak"));
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
      projectMembershipService.assignUserToProject("mpanzer1", "Fire Code Planning", ProjectRole.ADMIN);
      projectMembershipService.assignUserToProject("shusak1", "Exam Request System", ProjectRole.ADMIN);
      projectMembershipService.assignUserToProject("wlesniak1", "Exam Request System", ProjectRole.DEVELOPER);
      projectMembershipService.assignUserToProject("wlesniak1", "Fire Code Planning", ProjectRole.DEVELOPER);
      System.out.println("ProjectMembership seeded");
    }

  }
}