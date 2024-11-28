import React, {useState} from "react";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const [jwt, setJwt] = useState("");
    const [profile, setProfile] = useState(null);

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/signin",{
                method:"POST",
                headers:{
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({username, password}),
            }
            );

            if (response.ok) {
                const data = await response.json();
                console.log(data);
                setJwt(data.jwtToken)
                setMessage("Login Successful.");
                fetchUserProfile(data.jwtToken);
            } else {
                setMessage("Login Faild, Please check your credentials.");
            }

        } catch (error) {
            console.log("Error: " + error);
            setMessage("An error occured, please try again.");
        }
    };

    const fetchUserProfile = async (token) => {
        try {
            const response = await fetch("http://localhost:8080/profile",{
                method:"GET",
                headers:{
                    "Authorization": `Bearer ${token}`,
                },
            }
            );

            if (response.ok) {
                const data = await response.json();
                console.log(data);
                setProfile(data)
            } else {
                setMessage("Faild to fetch the profile");
            }

        } catch (error) {
            console.log("Error: " + error);
            setMessage("An error occured, please try again.");
        }
    };

    const handleLogout = () => {
        setUsername("");
        setPassword("");
        setJwt("");
        setProfile(null);
        setMessage("You Have Been Logged out.")
    }

    return (
        <div>
            {!profile ? (
            <form onSubmit={handleLogin}>
                <div>
                    <h2>LOGIN</h2>
                </div>
                <div>
                    <label>Username: </label>
                    <input 
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    />
                </div>
                <div>
                    <label>Password: </label>
                    <input 
                    type="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    />
                </div>
                <button type="submit">Login</button>
            </form>
            ) : (
                <div>
                    <h3>USER PROFILE</h3>
                    <p>Username: {profile.username}</p>
                    <p>Roles: {profile.roles.join(", ")}</p>
                    <p>Message: {profile.message}</p>
                    <button onClick={handleLogout}>Logout</button>
                </div>
            )}
            {message && <p>{message}</p>}
            {jwt && <p>{jwt}</p>}
        </div>
    );
}

export default Login;