async function iniciarSesion() {
    //Obtiene el id del paciente y su primer apellido
    var cipaut = document.getElementById('cipaut').value;
    var apellido = document.getElementById('apellido').value;

    try
    {
        //Solicita el token con la función verify
        const token = await verify(cipaut,apellido);
        //Navega al módulo frontend al cuál le pasa el token
        window.location.href = 'http://localhost/frontend/index.html?token=' + token;
    }
    catch (error)
    {
        alert("Error: " + error.message);
    }

}

//Lanza una petición a el módulo security para verificar el login
//Security devuelve un token JWT
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