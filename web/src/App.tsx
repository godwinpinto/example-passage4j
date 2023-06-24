import './App.css'
import Login from './pages/Login'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Dashboard from './pages/Dashboard'

function App() {

  return (
    <>
      <div>
      <BrowserRouter>
        <Routes>
          <Route element={<Login />} path="/" />
          <Route element={<Dashboard />} path="/dashboard" />
        </Routes>
      </BrowserRouter>

      </div>
      <p className="read-the-docs">
        Vite + React + Typescript + Top Java Frameworks
      </p>
    </>
  )
}

export default App
