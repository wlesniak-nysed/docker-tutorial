package gov.nysed.dockertutorial;

import gov.nysed.dockertutorial.enums.ProjectRole;
import gov.nysed.dockertutorial.model.Project;
import gov.nysed.dockertutorial.model.Task;
import gov.nysed.dockertutorial.model.User;
import gov.nysed.dockertutorial.repository.ProjectMembershipRepository;
import gov.nysed.dockertutorial.repository.ProjectRepository;
import gov.nysed.dockertutorial.repository.TaskRepository;
import gov.nysed.dockertutorial.repository.UserRepository;
import gov.nysed.dockertutorial.service.ProjectMembershipService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  private final UserRepository userRepository;
  private final ProjectRepository projectRepository;
  private final ProjectMembershipService projectMembershipService;
  private final ProjectMembershipRepository projectMembershipRepository;
  private final TaskRepository taskRepository;

  public DataInitializer(UserRepository userRepository, ProjectRepository projectRepository,
      ProjectMembershipService projectMembershipService,
      ProjectMembershipRepository projectMembershipRepository, TaskRepository taskRepository) {
    this.userRepository = userRepository;
    this.projectRepository = projectRepository;
    this.projectMembershipService = projectMembershipService;
    this.projectMembershipRepository = projectMembershipRepository;
    this.taskRepository = taskRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    Project project1 = null;
    Project project2 = null;
    if (userRepository.count() == 0) {
      userRepository.save(new User("aavery1","Anna", "Avery"));
      userRepository.save(new User("aavery2","Annaka","Avery"));
      userRepository.save(new User("aavery3","Alex","Avery"));
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
      project1 = projectRepository.save(new Project("Exam Request System"));
      projectRepository.save(new Project("Shipping Notice Generator"));
      projectRepository.save(new Project("Library Development Grants"));
      project2 = projectRepository.save(new Project("Fire Code Planning"));
      System.out.println("Projects seeded");
    }
    if (projectMembershipRepository.count() == 0) {
      projectMembershipService.assignUserToProject("mpanzer1", "Fire Code Planning", ProjectRole.ADMIN);
      projectMembershipService.assignUserToProject("shusak1", "Exam Request System", ProjectRole.ADMIN);
      projectMembershipService.assignUserToProject("wlesniak1", "Exam Request System", ProjectRole.DEVELOPER);
      projectMembershipService.assignUserToProject("wlesniak1", "Fire Code Planning", ProjectRole.DEVELOPER);
      System.out.println("ProjectMembership seeded");
    }
    if (taskRepository.count() == 0) {
      Task parentTask = null;
      if (project1 != null) {
        parentTask = taskRepository.save(new Task("Initialize Database", "Set up PostgreSQL for this project", project1));
        parentTask.addSubTask(new Task("Install PostgreSQL", "On your PC", project1));
        parentTask.addSubTask(new Task("Write create table scripts", "Include Users/Projects/Tasks", project1));
        taskRepository.save(parentTask);
        System.out.println("Exam Request System Tasks seeded");
      }
      if (project2 != null) {
        taskRepository.save(new Task("Set up Vue Front end", "Follow NYSED Bootstrap and accessibility standards", project2));
        System.out.println("Fire Code Planning Tasks seeded");
      }
    }
  }
}