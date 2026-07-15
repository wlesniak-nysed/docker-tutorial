//this file is a misnomer; it's really for all types, but this is a sample project so eh

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

export enum ProjectRole {
  ADMIN = 'ADMIN',
  DEVELOPER = 'DEVELOPER',
  VIEWER = 'VIEWER'
}

export const ProjectRoleLabels: Record<ProjectRole, string> = {
  [ProjectRole.ADMIN]: 'Administrator',
  [ProjectRole.DEVELOPER]: 'Developer',
  [ProjectRole.VIEWER]: 'Viewer'
};