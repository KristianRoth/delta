import React, { Component } from 'react';
import { Formik, Form, Field} from "formik"
import { CustomTextField } from "./CustomTextField"
import { Button } from "@material-ui/core"
import "../../css/Form.css"

interface FormProps {
}

interface FormState {
  errors: string
}

interface Values {
  username: string,
  email: string,
  password: string,
}

export default class SignUpForm extends Component<FormProps, FormState> {
    constructor(props: any, state: any) {
      super(props, state)
      this.state = {
        errors: ""
      }

    }

    sendSignUp = async (values: Values) => {
        let response = await fetch("/api/signUp", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(values),
        })
        if (response.status === 409) {
            console.log("username or email in use")
            this.setState({
              errors: "Username or email in use"
            })
            return;
        }
        if (response.status === 400) {
          let data = JSON.parse(await response.text())
          data.errors.map((error: any) => {
            console.log(error.defaultMessage)
            this.setState({
              errors: error.defaultMessage
            });
          });

          return;
        }
        let data = await response.json()
        localStorage.setItem("jwt", data.jwt)
    }

    render = () => {
        return (
            <Formik
              initialValues={{username: "", email: "", password: ""}}
              onSubmit={values => {this.sendSignUp(values)}} >
              {({ values }) => (
                <div className="signupWindow">
                  <h1 className="title">sign up</h1>
                  <Form>
                    <div className="inputdiv">
                      <Field
                        name="username"
                        placeholder="Username"
                        component={CustomTextField}
                      />
                    </div>
                    <div className="inputdiv">
                      <Field
                        type="email"
                        name="email"
                        placeholder="Email"
                        component={CustomTextField}
                      />
                    </div>
                    <div className="inputdiv">
                      <Field
                        type="password"
                        name="password"
                        placeholder="Password"
                        component={CustomTextField}
                      />
                    </div>
                    <Button style={{margin: "6px", backgroundColor: "#873a9e", color: "#ffffff", width: "90px"}} type="submit" >Submit</Button>
                    {this.state.errors.length !== 0 && <div className="error">{this.state.errors}</div>}
                    {/* <pre>{JSON.stringify(values, null, 2)}</pre> */}
                  </Form>
                </div>
              )}
            </Formik>
        )
    }
}
