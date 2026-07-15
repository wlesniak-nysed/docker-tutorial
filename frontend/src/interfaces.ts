export interface User {
  id: number;
  userName: string;
  firstName: string;
  lastName: string;
}

export interface Project {
  id: number;
  name: string;
}

export interface ProjectMembership {
  userId: number;
  userName: string;
  projectId: number;
  projectName: string;
  role: string;
}