
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


const getElement = id => {
    return document.getElementById(id);
};
