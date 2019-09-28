
const login = async () => {
    try {
        let response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `username=${getElement("email").value}&password=${getElement("password").value}`
        });
        if (response.status === 200) {
            window.location.reload();
        } else if (response.status === 401) {
            alert("Sorry, wrong username or password")
        } else {
            alert("Something went wrong, please try later")
        }
    } catch (error) {
        console.log("Error: ", error)
    }
};


const signIn = async () => {
    let username = document.getElementById('email').value;
    let password = document.getElementById('password').value;


    try {
        let response = await fetch("http://localhost:8080/api/users", {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({userName: username, password: password})
        });


        const data = await response.json();

        if (response.status === 409 || response.status === 403 || response.status === 401 || response.status === 500) {
            console.log(data.error);
            console.log(response);
        }else if (response.status === 200) {
            window.location.reload();
            console.log("Signed up!", data);
        }else {
            console.log(data.error);
            console.log(response);
        }


    } catch(error) {
        alert(error);
    }
};






const getElement = id => {
    return document.getElementById(id);
};
