async function iniciarSesion() {
    var cipaut = document.getElementById('cipaut').value;
    var apellido = document.getElementById('apellido').value;

    try
    {
        const token = await verify(cipaut,apellido);
        window.location.href = 'http://localhost/frontend/index.html?token=' + token;
    }
    catch (error)
    {
        alert("Error: " + error.message);
    }

}

async function verify(cipaut, apellido)
    {
        try {
            const response =await fetch("http://localhost/security/secure", {
                method: "POST",
                mode: "cors",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ id: cipaut, apellido:apellido }),})

            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`);
            }
            const result = await response.text();
            return result;

        } catch (error) {
            throw error;
        }
    }