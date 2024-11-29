import axios from "axios"
import React, { useEffect, useState } from "react";

const Dashboard = () => {

    const [user, setUser] = useState(null);

    useEffect(() => {
        axios.get('http://localhost:8080/user-info', {withCredentials: true})
        .then(Response => {
            setUser(Response.data);
        })
        .catch(error => {
            console.error('Error Occured: ', error);
        })
    }, []);

    return(
        <div>
            <h2>Dashboard</h2>
            {user ? (
            <div>
                <p><strong>Name:</strong> {user.name}</p>
                <p><strong>Email:</strong> {user.email}</p>
                {user.picture && <img src={user.picture}
                    alt="User Profile"
                        ReferrerPolicy="no-referrer"/>}
            </div>
            ) : (
                <p>Loading User Data....</p>
            )}
        </div>
        
    );
};

export default Dashboard;