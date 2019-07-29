
const sendPic = async () => {
    const myForm = new FormData();
    myForm.append("file", document.getElementById("file").files[0]);
        try {
            const response = await fetch('https://siqpik.herokuapp.com/picture/3', {
                method: 'POST',
                body: myForm
            });
            const data = await response.json();
            console.log(data);

        } catch (error) {
            console.log("Error: ", error)
        }
};
