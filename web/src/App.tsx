import React, { Component } from 'react';
import Button from './components/Button';
import SignUpForm from './components/form/SignUpFrom'
import LoginForm from './components/form/LoginForm'


export default class App extends Component<{}, {}> {

  sendPost = () => {
    fetch('/api/login',{
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        email: "test@example.org",
        password: "testtest"
      }),
    })
  }

  customGet = async () => {

    let response = await fetch("/admin/ping", {
      headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('jwt')
        }
      })

    console.log(await response.json())
  }



  render() {
    return (
      <div>
        <SignUpForm />
        {/* <Button text="Test Admin" onClick={this.customGet} /> */}
        {/* <LoginForm /> */}
      </div>
    )
  }
}
