document.addEventListener("DOMContentLoaded", function () {
  const puntajeForm = document.getElementById("puntajeForm");
  const totalPuntajeSpan = document.getElementById("totalPuntaje");

  puntajeForm.addEventListener("submit", function (event) {
    event.preventDefault();

    let puntajeTotal = 0;

    const categoriaInputs = puntajeForm.querySelectorAll(".categoria input");

    const puntajes = []; // Creamos un arreglo para almacenar los puntajes de actividades.

    categoriaInputs.forEach(function (input) {
      if (!isNaN(parseFloat(input.value))) {
        puntajeTotal += parseFloat(input.value);

        // Aquí también debes construir un objeto puntaje para cada actividad con el valor del input y el nombre de la actividad.
        const actividadNombre = input.name; // Suponemos que el atributo "name" de los inputs contiene el nombre de la actividad.
        const actividadPuntaje = parseFloat(input.value);

        const puntaje = {
          actividad: actividadNombre,
          puntaje: actividadPuntaje,
        };

        puntajes.push(puntaje);

        if (puntajeTotal > 100) {
          puntajeTotal = 100;
        }
      }
    });

    totalPuntajeSpan.textContent = puntajeTotal;

    // Luego, enviamos los puntajes a la API.
    enviarPuntajes(puntajes);
  });

  // Función para enviar puntajes a la API.
  function enviarPuntajes(puntajes) {
    fetch("/api/puntajes", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(puntajes), // Enviamos los puntajes como un arreglo de objetos en formato JSON.
    })
      .then(function (response) {
        if (response.ok) {
          return response.json();
        } else {
          throw new Error("Error al guardar los puntajes en la API.");
        }
      })
      .then(function (data) {
        // Aquí puedes manejar la respuesta de la API, si es necesario.
      })
      .catch(function (error) {
        console.error(error);
        // Aquí puedes manejar los errores de la API, si es necesario.
      });
  }
});
