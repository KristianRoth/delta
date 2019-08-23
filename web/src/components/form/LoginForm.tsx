import React, { Component } from 'react';
import { Formik, Form, Field} from "formik"
import { CustomTextField } from "./CustomTextField"
import { Button } from "@material-ui/core"

interface FormProps {
}

interface FormState {
}

interface Values {
  email: string,
  password: string,
}

export default class LoginForm extends Component<FormProps, FormState> {

    sendSignUp = async (values: Values) => {

        console.log(values)
        let response = await fetch("/api/login", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(values),
        })

        let data = await response.json()
        localStorage.setItem("jwt", data.jwt)
    }

    render = () => {
        return (
            <Formik
              initialValues={{email: "", password: ""}}
              onSubmit={values => {this.sendSignUp(values)}}

              >
              {({ values }) => (
                <Form>
                  <div>
                    <Field
                      type="email"
                      name="email"
                      placeholder="Email"
                      component={CustomTextField}
                    />
                  </div>
                  <div>
                    <Field
                      type="password"
                      name="password"
                      placeholder="Password"
                      component={CustomTextField}
                    />
                  </div>
                  <Button type="submit" >Submit</Button>
                  <pre>{JSON.stringify(values, null, 2)}</pre>
                </Form>
              )}
            </Formik>
        )
    }
}
