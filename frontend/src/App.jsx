import { BrowserRouter, Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar";
import Login from "./Pages/Login";

function Home() {
  return (
    <div className="min-h-screen bg-[#1a1a1a] flex justify-center items-center pt-16">
      <h2 className="text-4xl font-bold text-blue-600">Home</h2>
    </div>
  );
}

function Register() {
  return (
    <div className="min-h-screen bg-[#1a1a1a] flex justify-center items-center pt-16">
      <h2 className="text-white text-4xl font-bold">Register</h2>
    </div>
  );
}

function App() {
  return (
    <BrowserRouter>
      <div className="min-h-screen bg-[#1a1a1a]">
        <Navbar />
        <main className="pt-16">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
          </Routes>
        </main>
      </div>
    </BrowserRouter>
  );
}

export default App;