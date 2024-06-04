
let contador = 0;

function cambioDeEstadoCheck() {
    // Establecer el estado de todos los checkboxes en falso
    if (contador % 2 === 0) {
        $('[name="categoriasSeleccionadas"]').prop('checked', true);
    } else {
        $('[name="categoriasSeleccionadas"]').prop('checked', false);
    }
    contador++;
}



//Redirigir a página para crear profesor

document.addEventListener("DOMContentLoaded", function () {
  // Verificar si el botón existe antes de intentar agregar el event listener
  const crearProfesorButton = document.getElementById("crearProfesor");
  if (crearProfesorButton) {
    crearProfesorButton.addEventListener("click", function (event) {
      // Evitar que el enlace abra la página directamente
      event.preventDefault();

      // Valor específico que se quiere enviar en la solicitud POST
      var dniValue = 0;
      var url = "/profesor";

      // Formulario oculto dinámicamente
      var form = document.createElement("form");
      form.action = url;
      form.method = "POST";

      // Input para el valor de DNI
      var dniInput = document.createElement("input");
      dniInput.type = "number";
      dniInput.name = "dni";
      dniInput.value = dniValue; // Asignar el valor específico
      form.appendChild(dniInput);

      // Botón submit y agregarlo al formulario
      var submitButton = document.createElement("button");
      submitButton.type = "submit";
      submitButton.innerText = "Buscar";
      form.appendChild(submitButton);

      // Agregar el formulario al cuerpo del documento y enviarlo automáticamente
      document.body.appendChild(form);
      form.submit();
    });
  }
});
document.addEventListener("DOMContentLoaded", () => {
    const campoBusquedaPorId = document.getElementById("campoBusquedaPorID");
    const botonBuscarPorId = document.getElementById("botonBuscarPorID");
    const campoBusquedaPorDNI = document.getElementById("campoBusquedaPorDNI");
    const botonBuscarPorDNI = document.getElementById("botonBuscarPorDNI");

    const validarCampoYBoton = (campo, boton) => {
        const valorCampoBusqueda = campo.value.trim();
        boton.disabled = !valorCampoBusqueda;
    };

    const configurarListeners = (campo, boton) => {
        campo.addEventListener("input", () => {
            validarCampoYBoton(campo, boton);
        });
    };

    if (campoBusquedaPorId && botonBuscarPorId) {
        configurarListeners(campoBusquedaPorId, botonBuscarPorId);
        validarCampoYBoton(campoBusquedaPorId, botonBuscarPorId);
    }

    if (campoBusquedaPorDNI && botonBuscarPorDNI) {
        configurarListeners(campoBusquedaPorDNI, botonBuscarPorDNI);
        validarCampoYBoton(campoBusquedaPorDNI, botonBuscarPorDNI);
    }
});



//Detectar al menos un checkbox seleccionado al editar categorías

document.addEventListener("DOMContentLoaded", function () {
    // Verifica si estamos en la página "/categoria"
    const isCategoriasPage = window.location.pathname.includes("/categorias");

    if (isCategoriasPage) {
        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        const continuarButton = document.querySelector('button.ok');

        // Agrega un event listener al formulario para prevenir su envío si no hay checkbox seleccionados
        const form = document.querySelector("form");
        form.addEventListener("submit", function (event) {
            if (!alMenosUnCheckboxSeleccionado()) {
                event.preventDefault();
                alert("Selecciona al menos una categoría para continuar.");
            }
        });

        // Agrega un event listener a cada checkbox para verificar su estado y actualizar el botón "Continuar"
        checkboxes.forEach(function (checkbox) {
            checkbox.addEventListener("change", function () {
                actualizarBotonContinuar();
            });
        });

        // Función para verificar si al menos un checkbox está seleccionado
        function alMenosUnCheckboxSeleccionado() {
            return Array.from(checkboxes).some(function (checkbox) {
                return checkbox.checked;
            });
        }

        // Función para actualizar el estado del botón "Continuar"
        function actualizarBotonContinuar() {
            continuarButton.disabled = !alMenosUnCheckboxSeleccionado();
        }
    }
});

// Controla que en datos_personales.html se envien datos vacios
document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("guardar"); // Reemplaza "tuFormulario" con el ID de tu formulario

    form.addEventListener("submit", function(event) {
        // Obtener los campos del formulario
        const nombre = form.querySelector('input[name="nombre"]');
        const apellido = form.querySelector('input[name="apellido"]');
        const documento = form.querySelector('input[name="documento"]');
        const direccion = form.querySelector('input[name="direccion"]');
        const telefono = form.querySelector('input[name="telefono"]');

        // Verificar si los campos requeridos están vacíos
        if (nombre.value.trim() === '') {
            nombre.classList.add('error');
        } else {
            nombre.classList.remove('error');
        }

        if (apellido.value.trim() === '') {
            apellido.classList.add('error');
        } else {
            apellido.classList.remove('error');
        }

        if (documento.value.trim() === '') {
            documento.classList.add('error');
        } else {
            documento.classList.remove('error');
        }

        if (telefono.value.trim() === '') {
            telefono.classList.add('error');
        } else {
            telefono.classList.remove('error');
        }

        if (direccion.value.trim() === '') {
           direccion.classList.add('error');
        } else {
            direccion.classList.remove('error');
        }

        // Si algún campo requerido está vacío, evitar que el formulario se envíe
        if (nombre.value.trim() === '' || apellido.value.trim() === '' || documento.value.trim() === '' || direccion.value.trim() === '' || telefono.value.trim() === '') {
            // Evitar que el formulario se envíe
            event.preventDefault();

            // Mostrar un mensaje de error o tomar otra acción según sea necesario
            alert("Por favor complete todos los campos obligatorios.");
        }
    });
});

