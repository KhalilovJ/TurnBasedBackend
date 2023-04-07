const socket = new SockJS('/chat')
const stompClient = Stomp.over(socket)
let name;
window.addEventListener('load', (e) => {

    stompClient.connect({}, (frame) => {

        stompClient.subscribe('/topic/errors', (greeting) => {
            console.warn('errors: ' + greeting.body)
        });

        stompClient.subscribe('/topic/greetings', (greeting) => {
            console.log('greetings: ' + greeting.body)
        });

        stompClient.send('/app/chat', {}, JSON.stringify({name: name}))
    });
})

function send(){
    let text = document.getElementById("text").value;
    if (name == null){
        name = text;
    }
    stompClient.send('/app/chat', {}, JSON.stringify({name: name, content: text}))
}