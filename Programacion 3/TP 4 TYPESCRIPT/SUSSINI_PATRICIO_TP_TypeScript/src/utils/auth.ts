import { IUser } from '../types/IUser';
import { Rol } from '../types/Rol';

export const getUsers = (): IUser[] => {
    const users = localStorage.getItem('users');
    return users ? JSON.parse(users) : [];
};

export const saveUser = (user: IUser): void => {
    const users = getUsers();
    users.push(user);
    localStorage.setItem('users', JSON.stringify(users));
};

export const loginUser = (userData: IUser): void => {
    localStorage.setItem('userData', JSON.stringify(userData));
};

export const logoutUser = (): void => {
    localStorage.removeItem('userData');
};

export const getActiveUser = (): IUser | null => {
    const user = localStorage.getItem('userData');
    return user ? JSON.parse(user) : null;
};

export const isAuthenticated = (): boolean => {
    return getActiveUser() !== null;
};

export const hasRole = (role: Rol): boolean => {
    const user = getActiveUser();
    return user ? user.rol === role : false;
};