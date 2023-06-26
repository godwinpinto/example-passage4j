import { PassageElement } from "@passageidentity/passage-auth/dist/package/index.js";
import '@passageidentity/passage-elements/passage-auth'
import { useEffect } from "react";
import axios from 'axios';
import { authState } from "../store/Auth";
import { useSetRecoilState } from "recoil";
import { useNavigate } from "react-router-dom";



interface authResult {
  redirect_url: string;
  auth_token: string;
  refresh_token?: string; // only if you have refresh tokens enabled.
  refresh_token_expiration?: number; // only if you have refresh tokens enabled
}


const loginRequest = async (authResult:authResult):Promise<string>  => {
  try {
    // Set the headers with the authentication token
    const headers = {
      Authorization: `Bearer ${authResult.auth_token}`,
      'Content-Type': 'application/json',
    };

    // Make the POST request using Axios
    const response = await axios.post('/api/login', authResult, { headers });
    if (response.status === 200) {
      return "OK";
    } else {
      return "NO";
    }
  } catch (error) {
    // Handle any error that occurred during the POST request
    console.error('Error:', error);
    return "NO"
  }
};


export const Login =()=> {
  const navigate = useNavigate();

  const setAuthHeader = useSetRecoilState(authState);

  const onSuccess = async (authResult: authResult) => {
    setAuthHeader(authResult.auth_token);
    const status = await loginRequest(authResult);
    if(status==='OK'){
    window.location.href = authResult.redirect_url
    }else{
      window.location.href = "/"
    }
  }

  useEffect(() => {
    const setQuerySelector = () => {
      const passageAuth = document.querySelector("passage-auth") as PassageElement
      passageAuth.onSuccess = onSuccess
    };
    window.addEventListener('load', setQuerySelector);
    return () => {
      window.removeEventListener('load', setQuerySelector);
    };
  }, []);

  const PASSAGE_APP_ID = import.meta.env.VITE_PASSAGE_APP_ID;

  return (
    <>
      <div className="container">
        <h1>Passage4J + Java Frameworks Demo</h1>
        <passage-auth app-id={PASSAGE_APP_ID} ></passage-auth>
      </div>
    </>
  );
}
