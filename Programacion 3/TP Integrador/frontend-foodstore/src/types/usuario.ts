export type Rol = 'ADMIN' | 'USUARIO';

export interface Usuario {
  id: number;
  nombre: string;
  apellido: string;
  mail: string;
  celular: string;
  password: string;
  rol: Rol;
}

/** Usuario en sesión: igual a Usuario pero sin password. */
export type SesionUsuario = Omit<Usuario, 'password'>;
