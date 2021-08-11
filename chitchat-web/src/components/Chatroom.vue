<template>
  <div id="chatroom">
    <div class="message-body mt-3">
      <h3>Chat</h3>
      <h5>Welcome {{ name }}!</h5>
      <div class="card">
        <div class="card-body">
          <div
            class="border pl-2 pt-1 ml-2 message-text mb-2"
            v-for="(message, idx) in messages"
            :key="idx"
          >
            <span class="mg-text">{{ message.userName }}</span>
            <p class="message pt-1">{{ message.content }}</p>
          </div>
        </div>
      </div>
      <input v-model="newMessage" type="text" class="mt-3 mr-2 pl-2 pr-2" />
      <button class="btn btn-primary" @click="send">Send</button>
    </div>
  </div>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name: "Chatroom",
  components: {},

  data() {
    return {
      username: "",
      name: null,
      messages: [],
      newMessage: null,
      typing: false,
      ready: false,
      info: [],
      connections: 0,
    };
  },

  methods: {
    send() {
      console.log("Send message:" + this.newMessage);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { content: this.newMessage //, userName userId sendDate
        };
        this.stompClient.send("/app/hello", JSON.stringify(msg), {});
      }
    },

    
    connect() {
      this.socket = new SockJS("http://localhost:8081/chitchat/chat");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log(frame);
          this.stompClient.subscribe("/topic/greetings", tick => {
            console.log(tick);
            this.messages.push(JSON.parse(tick.body));
          });
        },
        error => {
          console.log(error);
          this.connected = false;
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
      this.connected = false;
    },
  },
  mounted() {
    this.connect();
  }

  /*data() {
    return {
      username: "",
      name: null,
      messages: [],
      newMessage: null,
      typing: false,
      ready: false,
      info: [],
      connections: 0,
    };
  },

  methods: {
    send() {
      this.messages.push({
        message: this.newMessage,
        type: 0,
        user: "Me",
      });

      this.$socket.emit("chat-message", {
        message: this.newMessage,
        user: this.username,
      });
      this.newMessage = null;
    },

    addUser() {
      this.ready = true;
      this.$socket.emit("joined", this.username);
    },
  },

  watch: {
    newMessage(value) {
      value ? this.$socket.emit("typing", this.username) : this.$socket.emit("stopTyping");
    },
  },

  created() {
    window.onbeforeunload = () => {
      this.$socket.emit("leave", this.username);
    };

    this.$socket.on("chat-message", (data) => {
      this.messages.push({
        message: data.message,
        type: 1,
        user: data.user,
      });
    });

    this.$socket.on("typing", (data) => {
      this.typing = data;
    });

    this.$socket.on("stopTyping", () => {
      this.typing = false;
    });

    this.$socket.on("joined", (data) => {
      this.info.push({
        username: data,
        type: "joined",
      });

      setTimeout(() => {
        this.info = [];
      }, 5000);
    });

    this.$socket.on("leave", (data) => {
      this.info.push({
        username: data,
        type: "left",
      });

      setTimeout(() => {
        this.info = [];
      }, 5000);
    });

    this.$socket.on("connections", (data) => {
      this.connections = data;
    });
  },*/
};
</script>