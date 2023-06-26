import { atom } from "recoil";

// states can be created using atom API
export const authState = atom<string>({key:"",default:localStorage.getItem('authToken') ?? ''})