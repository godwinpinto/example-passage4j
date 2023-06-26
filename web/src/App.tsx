import './App.css'
import { Dashboard } from './pages/Dashboard'
import {  Route, Routes } from 'react-router-dom'
import { Login } from './pages/Login'


function App() {

  return (
    <>
      <div>
            <Routes>
              <Route element={<Login />} path="/" />
              <Route element={<Dashboard />} path="/dashboard" />
            </Routes>

      </div>
      <p className="read-the-docs">
        Vite + React + Typescript + Top Java Frameworks
      </p>
    </>
  )
}

export default App
