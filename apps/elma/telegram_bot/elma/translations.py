translations = {
    "yes": "Sí",
    "no": "No",
    "start": "Soy Elma y voy a comenzar a recordarte que bebas agua",
    "ask-drink-water": "¿Has bebido agua?",
    "ask-drink-source": "¿Qué has usado para beber?",
    "a-bottle": "Una botella",
    "my-bottle": "Mi botella",
    "new-bottle": "Una botella nueva",
    "glass": "Un vaso",
    "ask-bottle-capacity": "¿De cuanta capacidad es tu botella de agua? (en litros)",
    "bottle-capacity-value-error": "La capacidad de la botella no es valida",
    "ask-drinked-amount": "¿Cuanta agua has bebido de la botella?",
    "less-than-middle": "Menos de la mitad",
    "middle": "La mitad",
    "more-than-middle": "Mas de la mitad",
    "create_user_error": "Ha ocurrido un error al conectarse con la API vuelve a intentar ejecutar el comando /start mas tarde",
    "get_user_error": "Ha ocurrido un error al recuperar tus registros",
    "create_bottle_error": "Ha ocurrido un error al registrar tu botella, intentalo de nuevo",
    "create_water_step_error": "Ha ocurrido un error al registrar la cantidad de agua que has bebido",
    "create_glass_error": "Ha ocurrido un error al registrar el vaso que has bebido",
}


def translation(key: str):
    if key not in translations:
        return key

    return translations[key]
