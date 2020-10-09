function getIndex(list,id){
    for(var i =0;i<list.length;i++){
        if(list[i] == id){
            return i;
        }
    }
}

var messageApi = Vue.resource('/data{/id}')
Vue.component ('message-form', {
    props: ['messages','messageAtr'],
    data: function () {
        return {
            id:'',
            name: '',
            address: ''}
    },
    watch:{
        messageAtr:function(newVal,oldVal) {
            this.id=newVal.id;
            this.name = newVal.name;
            this.address = newVal.address;
        }
    },
    template:
    '<div>' +
        '<input type="text" placeholder="name" v-model="name" />' +
        '<input type="text" placeholder="address" v-model = "address" />'+
        '<input type="button" value="Save" v-on:click = "save">'+
        '</div>',

    methods:{
        save:function (){
            var message = {name:this.name,
                        address: this.address};
            if(this.id){
                messageApi.update({id:this.id},message).then(result=>
                result.json().then(data=>{
                    let index = getIndex(this.messages,data.id);
                    this.messages.splice(index,1,data);
                    this.name = '';
                    this.address = '';
                    })
                )
            } else {
                messageApi.save({}, message).then(result =>
                    result.json().then(data => {
                            this.messages.push(data);
                            this.id='';
                            this.name = '';
                            this.address = '';
                        }
                    )
                )
            }
        }
    }
});
Vue.component('message-row', {
    props:['message','editMethod','messages'],

    template:'<div> ' +
        '<i>({{ message.id }})</i> {{message.name}}-{{message.address}} ' +
        '<span>' +
        '   <input type="button" value="Edit" v-on:click ="edit" /> ' +
        '   <input type="button" value="X" v-on:click ="del">' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.message);
        },
        del:function () {
            messageApi.remove({id: this.message.id}).then(result => {
                if(result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message))
                }
            })
        }
    }
});

Vue.component('messages-list', {
    props:['messages'],
    data:function (){
        return {
            message: null
        }
    },
    template:'<div>' +
        '<message-form :messages="messages" :messageAtr="message"/>'+
        '<message-row v-for="message in messages" :key="message.id" :message="message" :editMethod="editMethod" :messages = "messages"/> ' +
        '</div>',
    created: function () {
        messageApi.get().then(result =>
            result.json().then(data=>
            data.forEach(message => this.messages.push(message))
            )
        )
    },
    methods:{
        editMethod:function (message){
            this.message = message;
        }
    }
});

var app = new Vue({
    el:'#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});
