import { fetchUsuarios } from './api';
import type { Rol, SesionUsuario, Usuario } from '../types/usuario';

const KEY_SESSION = 'foodstore_session';
const KEY_REGISTRADOS = 'foodstore_usuarios_registrados';

/**
 * Los usuarios registrados en esta iteración no se persisten en usuarios.json
 * (es solo estado local, ver F4.1 de la consigna). Se guardan en localStorage
 * en vez de una variable en memoria porque cada página de esta app
 * multi-página es una recarga completa del documento.
 */
function getRegistrados(): Usuario[] {
  const raw = localStorage.getItem(KEY_REGISTRADOS);
  return raw ? (JSON.parse(raw) as Usuario[]) : [];
}

function guardarRegistrado(usuario: Usuario): void {
  const registrados = getRegistrados();
  registrados.push(usuario);
  localStorage.setItem(KEY_REGISTRADOS, JSON.stringify(registrados));
}

/** Todos los usuarios disponibles para login: los del JSON + los registrados en esta sesión del navegador. */
export async function fetchUsuariosCombinados(): Promise<Usuario[]> {
  const base = await fetchUsuarios();
  return [...base, ...getRegistrados()];
}

export function getSesion(): SesionUsuario | null {
  const raw = localStorage.getItem(KEY_SESSION);
  return raw ? (JSON.parse(raw) as SesionUsuario) : null;
}

function quitarPassword(usuario: Usuario): SesionUsuario {
  const copia: Partial<Usuario> = { ...usuario };
  delete copia.password;
  return copia as SesionUsuario;
}

function setSesion(usuario: Usuario): void {
  localStorage.setItem(KEY_SESSION, JSON.stringify(quitarPassword(usuario)));
}

export function logout(): void {
  localStorage.removeItem(KEY_SESSION);
}

export async function login(mail: string, password: string): Promise<SesionUsuario> {
  const usuarios = await fetchUsuariosCombinados();
  const encontrado = usuarios.find(u => u.mail.toLowerCase() === mail.toLowerCase());
  if (!encontrado || encontrado.password !== password) {
    throw new Error('Mail o contraseña incorrectos.');
  }
  setSesion(encontrado);
  return quitarPassword(encontrado);
}

export async function registrar(nombre: string, apellido: string, mail: string, password: string): Promise<SesionUsuario> {
  const usuarios = await fetchUsuariosCombinados();
  if (usuarios.some(u => u.mail.toLowerCase() === mail.toLowerCase())) {
    throw new Error('Ya existe una cuenta con ese mail.');
  }
  const maxId = usuarios.reduce((max, u) => Math.max(max, u.id), 0);
  const nuevo: Usuario = {
    id: maxId + 1,
    nombre,
    apellido,
    mail,
    celular: '',
    password,
    rol: 'USUARIO'
  };
  guardarRegistrado(nuevo);
  setSesion(nuevo);
  return quitarPassword(nuevo);
}

const RUTA_LOGIN = '/src/pages/auth/login/login.html';
const RUTA_ADMIN_HOME = '/src/pages/admin/adminHome/adminHome.html';
const RUTA_STORE_HOME = '/src/pages/store/home/home.html';

/** Redirige al login si no hay sesión. Si se pasan roles, exige que el usuario tenga uno de ellos. */
export function exigirSesion(rolesPermitidos?: Rol[]): SesionUsuario | null {
  const sesion = getSesion();
  if (!sesion) {
    location.replace(RUTA_LOGIN);
    return null;
  }
  if (rolesPermitidos && !rolesPermitidos.includes(sesion.rol)) {
    location.replace(sesion.rol === 'ADMIN' ? RUTA_ADMIN_HOME : RUTA_STORE_HOME);
    return null;
  }
  return sesion;
}

export function rutaHomePorRol(rol: Rol): string {
  return rol === 'ADMIN' ? RUTA_ADMIN_HOME : RUTA_STORE_HOME;
}
