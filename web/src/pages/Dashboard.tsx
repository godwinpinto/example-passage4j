import axios from "axios";
import { useEffect, useState } from "react";
import { authState } from "../store/Auth";
import { useRecoilState, useRecoilValue } from "recoil";
import 'bootstrap/dist/css/bootstrap.css';

const getCookieValue = (name:string):string => {
  const cookies = document.cookie.split('; ');
  for (let i = 0; i < cookies.length; i++) {
    const cookie = cookies[i].split('=');
    if (cookie[0] === name) {
      return cookie[1];
    }
  }
  return '';
};

const tableSytle={
  width:"700px"
}

const isDateValue = (value:any) => {
  return typeof value === 'number' && value > 0 && new Date(value).getTime() > 0;
};
const formatDateTime = (value:number) => {
  const date = new Date(value);
  return date.toLocaleString();
};
export const Dashboard=()=> {
  const [data, setData] = useState([]);
  const [authHeader, setAuthHeader] = useRecoilState(authState);
  const fetchData = async () => {
    if(!authHeader){
      setAuthHeader(getCookieValue('psg_auth_token'));
    }
    const bearer=authHeader?authHeader:getCookieValue('psg_auth_token');
    try {
      const headers = {
        Authorization: `Bearer ${bearer}`,
        'Content-Type': 'application/json',
      };
      const response = (await axios.get('/api/dashboard', { headers: headers }));
      setData(response.data);
    } catch (error) {
      //console.log('Error fetching data:', error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);



  return (
    <>

<div className="container">
     <h1>Dashboard</h1>
     <h3>Welcome you are now logged in</h3>

      <table className="table table-striped-columns" style={tableSytle}>
        <thead>
          <tr>
            <th className="text-start">Key</th>
            <th className="text-start">Value</th>
          </tr>
        </thead>
        <tbody>
          {Object.entries(data).map(([key, value])=>(
            <tr key={key}>
              <td className="text-start">{key}</td>
              <td className="text-start">{isDateValue(value) ? formatDateTime(value) : String(value)}</td>
            </tr>
          ))}
        </tbody>
      </table>
      </div>
    </>
  );
}
