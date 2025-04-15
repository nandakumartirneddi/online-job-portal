// ✅ Save token to localStorage
export const saveToken = (token) => {
    localStorage.setItem("token", token);
  };
  
  // ✅ Get token from localStorage
  export const getToken = () => {
    return localStorage.getItem("token");
  };
  
  // ✅ Remove token from localStorage
  export const clearToken = () => {
    localStorage.removeItem("token");
  };