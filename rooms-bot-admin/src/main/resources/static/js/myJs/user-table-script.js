function send() {
    let jsonText = httpGet('http://95.217.184.62:8080/api/user')
    let users = JSON.parse(jsonText)
    let inputId = []
    for (user of users) {
        element = document.getElementById(user.idTelegram)
        if (element.checked) {
            inputId.push(user.idTelegram)
        }
    }
    msg = document.getElementById('msg_editor')
    sendOdj = {
        'userTelegramId': inputId,
        'messageText': msg.value
    }
    fetch('http://95.217.184.62:8080/api/message/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(sendOdj),
    })
        .then((response) => response.json())
        .then((data) => {
            console.log('Save:', data);
        })
        .catch((error) => {
            console.error('Error:', error);
        });
}

function httpGet(theUrl) {
    let xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, async = false); // false for synchronous request
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

