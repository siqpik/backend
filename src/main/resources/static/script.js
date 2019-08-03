
const sendPic = async () => {
    const myForm = new FormData();
    myForm.append("file", document.getElementById("file").files[0]);
        try {
            const response = await fetch('http://localhost:8080/picture/3', {
                method: 'POST',
                body: myForm
            });
            const data = await response.json();
            console.log(data);

        } catch (error) {
            console.log("Error: ", error)
        }
};

const getUser = async () => {
    try {
        let response = await fetch('http://localhost:8080/loggedUser', {
            method: 'GET',
        });
        console.log(response.status);
        if(response.status === 200) {
            let user = await response.json();
            console.log(user);
            getProfile(user.id);
            getAdmirers(user.id);
            getAdmirerings(user.id);
        } else if (response.status === 204) {
            console.log("Nobody is log in");
        } else {
            console.log("ssssss")
        }
    } catch (error) {
        console.log("Error: ", error)
    }
};

const getProfile = async (id) => {
    try {
        let response = await fetch(`http://localhost:8080/profile/${id}`, {
            method : "Get"
        });
        console.log(response.status);
        if (response.status === 200) {
            let data = await response.json();
            console.log(data);
        } else if (response.status === 404) {
            console.log("This user does not exist");
        } else if (response.status === 401) {
            console.log("You are not log in");
        } else {
            console.log("Something went wrong")
        }
    } catch (error) {
        console.log("Error: ", error)
    }
};

const getAdmirers = async (id) => {
    try {
        let response = await fetch(`http://localhost:8080/admirers/${id}`, {
            method: "GET"
        });
        console.log(response.status);
        if (response.status === 200) {
            let data = await response.json();
            console.log("Admirers", data);
        } else if (response.status === 403) {
            console.log("You can only see your own Admirers");
        } else if (response.status === 401) {
            console.log("You are not log in");
        } else {
            console.log("Something went wrong")
        }
    } catch (e) {
        console.log("Error: ", error)
    }
};

const getAdmirerings = async (id) => {
    try {
        let response = await fetch(`http://localhost:8080/admiring/${id}`, {
            method: "GET"
        });
        console.log(response.status);
        if (response.status === 200) {
            let data = await response.json();
            console.log("Admiring", data);
        } else if (response.status === 403) {
            console.log("You can only who are you admiring");
        } else if (response.status === 401) {
            console.log("You are not log in");
        } else {
            console.log("Something went wrong")
        }
    } catch (e) {
        console.log("Error: ", error)
    }
};

getUser();
