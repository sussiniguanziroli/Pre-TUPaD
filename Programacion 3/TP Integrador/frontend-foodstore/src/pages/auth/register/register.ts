import { getSesion, registrar, rutaHomePorRol } from '../../../utils/auth';

const sesionActual = getSesion();
if (sesionActual) {
  location.replace(rutaHomePorRol(sesionActual.rol));
}

const form = document.getElementById('register-form') as HTMLFormElement;
const alertBox = document.getElementById('alert-box') as HTMLDivElement;

function mostrarError(mensaje: string): void {
  alertBox.innerHTML = `<div class="form-error">${mensaje}</div>`;
}

function emailValido(mail: string): boolean {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(mail);
}

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  alertBox.innerHTML = '';

  const nombreCompleto = (document.getElementById('nombre') as HTMLInputElement).value.trim();
  const mail = (document.getElementById('mail') as HTMLInputElement).value.trim();
  const password = (document.getElementById('password') as HTMLInputElement).value;

  if (!nombreCompleto || !mail || !password) {
    mostrarError('Completá todos los campos.');
    return;
  }
  if (!emailValido(mail)) {
    mostrarError('Ingresá un email con formato válido.');
    return;
  }
  if (password.length < 6) {
    mostrarError('La contraseña debe tener al menos 6 caracteres.');
    return;
  }

  const [nombre, ...resto] = nombreCompleto.split(' ');
  const apellido = resto.join(' ');

  try {
    const usuario = await registrar(nombre, apellido, mail, password);
    location.href = rutaHomePorRol(usuario.rol);
  } catch (err) {
    mostrarError(err instanceof Error ? err.message : 'Error al registrarse.');
  }
});
