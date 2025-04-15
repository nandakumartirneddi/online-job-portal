import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { saveToken } from "../Utils/auth";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setIsLoading(true);
        setError("");
        
        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                },
                credentials: 'include',
                body: JSON.stringify({email, password}),
            });

            if (!response.ok) {
                const errorData = await response.json().catch(() => null);
                throw new Error(errorData?.message || "Login failed. Please check your credentials.");
            }

            const data = await response.json();
            console.log("✅ Login successful");
            saveToken(data.token);
            console.log("Token saved in storage: ", localStorage.getItem("token"));
            setEmail("");
            setPassword("");
            navigate('/');
        } catch (error) {
            console.error("❌ Login Error:", error);
            setError(error.message || "Unable to connect to the server. Please try again later.");
        } finally {
            setIsLoading(false);
        }
    }

    return (
        <div className="min-h-[calc(100vh-64px)] bg-gradient-to-b from-white to-blue-50 flex items-center px-4 py-8">
            <div className="container mx-auto max-w-6xl flex flex-col md:flex-row items-start justify-between gap-8 lg:gap-12">
                {/* Left side - Illustration */}
                <div className="w-full md:w-3/5 flex flex-col items-center md:items-start pt-4">
                    <div className="w-full max-w-3xl mx-auto p-4 relative group">
                        <div className="absolute inset-0 bg-gradient-to-r from-blue-600/20 to-blue-400/20 rounded-2xl blur-xl group-hover:blur-2xl transition-all duration-300"></div>
                        <img 
                            src="/4565.jpg" 
                            alt="Job Search Illustration" 
                            className="w-full h-auto rounded-2xl relative z-10 shadow-[0_8px_30px_rgb(59,130,246,0.2)] group-hover:shadow-[0_8px_40px_rgb(59,130,246,0.3)] group-hover:scale-102 transition-all duration-300"
                        />
                    </div>
                    <div className="text-center md:text-left w-full max-w-3xl mx-auto mt-8 px-4">
                        <h1 className="text-5xl lg:text-6xl font-bold bg-gradient-to-r from-blue-600 to-blue-400 bg-clip-text text-transparent drop-shadow-[0_0_8px_rgba(59,130,246,0.3)]">
                            Find Your Dream Job
                        </h1>
                        <p className="mt-6 text-gray-600 text-xl leading-relaxed max-w-2xl">
                            Connect with top employers and discover opportunities that match your skills and aspirations.
                        </p>
                    </div>
                </div>

                {/* Right side - Login Form */}
                <div className="w-full md:w-2/5 flex justify-center">
                    <div className="w-full max-w-md space-y-6 p-4">
                        <div className="text-center md:text-left space-y-3">
                            <h2 className="text-3xl font-bold text-transparent bg-clip-text bg-gradient-to-r from-blue-600 to-blue-400">
                                Welcome
                            </h2>
                            <div className="space-y-2">
                                <p className="text-gray-600 font-medium">
                                    ✨ Leave your powerful mark
                                </p>
                                <p className="text-gray-600 font-medium">
                                    🌟 Embrace your uniqueness
                                </p>
                                <p className="text-gray-600 font-medium">
                                    🚀 Discover impactful opportunities
                                </p>
                            </div>
                        </div>

                        {error && (
                            <div className="bg-red-50 border border-red-200 rounded-lg p-4 mb-6">
                                <p className="text-red-500 text-center text-sm">{error}</p>
                            </div>
                        )}

                        <form onSubmit={handleLogin} className="bg-white/70 backdrop-blur-sm p-8 rounded-xl shadow-[0_8px_30px_rgb(0,0,0,0.08)] border border-blue-100">
                            <div className="mb-6">
                                <label htmlFor="email" className="block text-gray-700 text-sm font-medium mb-2">
                                    Email Address
                                </label>
                                <input 
                                    type="email"  
                                    value={email} 
                                    onChange={(e) => setEmail(e.target.value)}  
                                    required 
                                    id="email" 
                                    className="w-full px-4 py-3 rounded-lg bg-white border border-blue-200 focus:border-blue-400 focus:ring-2 focus:ring-blue-100 text-gray-700 placeholder-gray-400 transition-all duration-200 shadow-[0_2px_8px_rgb(59,130,246,0.05)]"
                                    placeholder="Enter your email"
                                    disabled={isLoading}
                                />
                            </div>
                            <div className="mb-6">
                                <label htmlFor="password" className="block text-gray-700 text-sm font-medium mb-2">
                                    Password
                                </label>
                                <input 
                                    type="password" 
                                    value={password} 
                                    onChange={(e) => setPassword(e.target.value)} 
                                    required 
                                    id="password" 
                                    className="w-full px-4 py-3 rounded-lg bg-white border border-blue-200 focus:border-blue-400 focus:ring-2 focus:ring-blue-100 text-gray-700 placeholder-gray-400 transition-all duration-200 shadow-[0_2px_8px_rgb(59,130,246,0.05)]"
                                    placeholder="Enter your password"
                                    disabled={isLoading}
                                />
                            </div>
                            <button 
                                type="submit" 
                                className="w-full bg-gradient-to-r from-blue-500 to-blue-400 text-white py-3 rounded-lg font-medium transition-all duration-200 hover:shadow-[0_0_20px_-5px_rgba(59,130,246,0.5)] hover:from-blue-600 hover:to-blue-500 disabled:opacity-50 disabled:cursor-not-allowed"
                                disabled={isLoading}
                            >
                                {isLoading ? "Signing In..." : "Sign In"}
                            </button>

                            <div className="mt-6 text-center">
                                <p className="text-gray-600">
                                    Don't have an account?{' '}
                                    <Link to="/register" className="text-blue-500 hover:text-blue-600 font-medium transition-colors duration-200">
                                        Sign up
                                    </Link>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;