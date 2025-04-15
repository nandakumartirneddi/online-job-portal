import { Link } from "react-router-dom";
import { useState } from "react";

function Navbar() {
    const [isMenuOpen, setIsMenuOpen] = useState(false);

    return (
        <nav className="bg-gradient-to-r from-blue-600 via-blue-400 to-blue-600 fixed w-full top-0 z-50 before:absolute before:inset-0 before:bg-gradient-to-b before:from-white/20 before:to-transparent before:z-0 shadow-[0_2px_20px_-3px_rgba(59,130,246,0.5)] backdrop-blur-sm bg-opacity-90">
            <div className="relative z-10 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div className="flex justify-between items-center h-16">
                    {/* Logo and brand */}
                    <div className="flex items-center">
                        <Link to="/" className="flex items-center">
                            <span className="text-2xl font-bold text-white drop-shadow-[0_0_12px_rgba(255,255,255,0.4)]">
                                JobZilla
                            </span>
                        </Link>
                    </div>

                    {/* Mobile menu button */}
                    <div className="md:hidden">
                        <button
                            onClick={() => setIsMenuOpen(!isMenuOpen)}
                            className="inline-flex items-center justify-center p-2 rounded-md text-white/90 hover:text-white hover:bg-white/20 focus:outline-none transition-all duration-200 hover:shadow-[0_0_10px_rgba(255,255,255,0.2)]"
                        >
                            <svg
                                className="h-6 w-6"
                                fill="none"
                                viewBox="0 0 24 24"
                                stroke="currentColor"
                            >
                                {isMenuOpen ? (
                                    <path
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                        strokeWidth={2}
                                        d="M6 18L18 6M6 6l12 12"
                                    />
                                ) : (
                                    <path
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                        strokeWidth={2}
                                        d="M4 6h16M4 12h16M4 18h16"
                                    />
                                )}
                            </svg>
                        </button>
                    </div>

                    {/* Desktop Navigation */}
                    <div className="hidden md:flex items-center space-x-4">
                        <Link 
                            to="/login" 
                            className="text-white/90 hover:text-white px-4 py-2 rounded-lg text-sm font-medium transition-all duration-200 hover:bg-gradient-to-b hover:from-white/20 hover:to-white/5 hover:shadow-[0_0_15px_rgba(255,255,255,0.2)]"
                        >
                            Login
                        </Link>
                        <Link 
                            to="/" 
                            className="text-white/90 hover:text-white px-4 py-2 rounded-lg text-sm font-medium transition-all duration-200 hover:bg-gradient-to-b hover:from-white/20 hover:to-white/5 hover:shadow-[0_0_15px_rgba(255,255,255,0.2)]"
                        >
                            Home
                        </Link>
                        <Link 
                            to="/jobs" 
                            className="text-white/90 hover:text-white px-4 py-2 rounded-lg text-sm font-medium transition-all duration-200 hover:bg-gradient-to-b hover:from-white/20 hover:to-white/5 hover:shadow-[0_0_15px_rgba(255,255,255,0.2)]"
                        >
                            Jobs
                        </Link>
                        <Link 
                            to="/register" 
                            className="bg-gradient-to-b from-white to-blue-50 text-blue-600 px-4 py-2 rounded-lg text-sm font-medium transition-all duration-200 hover:shadow-[0_0_20px_rgba(255,255,255,0.4)] hover:from-white hover:to-white border border-white/30 backdrop-blur-sm"
                        >
                            Sign Up
                        </Link>
                    </div>
                </div>

                {/* Mobile Navigation */}
                <div className={`${isMenuOpen ? 'block' : 'hidden'} md:hidden bg-gradient-to-r from-blue-600 via-blue-400 to-blue-600 rounded-b-lg shadow-lg before:absolute before:inset-0 before:bg-gradient-to-b before:from-white/20 before:to-transparent before:rounded-b-lg before:z-0 relative border-t border-white/20`}>
                    <div className="relative z-10 px-2 pt-2 pb-3 space-y-1 sm:px-3">
                        <Link 
                            to="/login" 
                            className="text-white/90 hover:text-white block px-3 py-2 rounded-lg text-base font-medium transition-all duration-200 hover:bg-gradient-to-b hover:from-white/20 hover:to-white/5 hover:shadow-[0_0_15px_rgba(255,255,255,0.2)]"
                        >
                            Login
                        </Link>
                        <Link 
                            to="/" 
                            className="text-white/90 hover:text-white block px-3 py-2 rounded-lg text-base font-medium transition-all duration-200 hover:bg-gradient-to-b hover:from-white/20 hover:to-white/5 hover:shadow-[0_0_15px_rgba(255,255,255,0.2)]"
                        >
                            Home
                        </Link>
                        <Link 
                            to="/jobs" 
                            className="text-white/90 hover:text-white block px-3 py-2 rounded-lg text-base font-medium transition-all duration-200 hover:bg-gradient-to-b hover:from-white/20 hover:to-white/5 hover:shadow-[0_0_15px_rgba(255,255,255,0.2)]"
                        >
                            Jobs
                        </Link>
                        <Link 
                            to="/register" 
                            className="bg-gradient-to-b from-white to-blue-50 text-blue-600 block px-3 py-2 rounded-lg text-base font-medium transition-all duration-200 hover:shadow-[0_0_20px_rgba(255,255,255,0.4)] hover:from-white hover:to-white border border-white/30 backdrop-blur-sm"
                        >
                            Sign Up
                        </Link>
                    </div>
                </div>
            </div>
        </nav>
    );
}

export default Navbar;