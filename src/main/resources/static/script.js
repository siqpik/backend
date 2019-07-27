
const sendPic = async () => {
    const myForm = new FormData();
    myForm.append("file", document.getElementById("file").files[0]);
        try {
            const response = await fetch('http://localhost:8080/image', {
                method: 'POST',
                body: myForm
            });
            const data = await response.json();
            console.log(data);

        } catch (error) {
            console.log("Error: ", error)
        }
};
