import React from "react"
import { TextField } from "@material-ui/core"
import { FieldProps } from "formik"

interface Props extends FieldProps {
  placeholder: string
  type: string
}

export const CustomTextField: React.FC<Props> = ({placeholder, type,  field}) => {
  return <TextField
      inputProps={{
        style: {
          textAlign: "center",
          width: "300px"
        }
      }}
      required
      margin="dense"
      variant='outlined'
      type={type}
      label={placeholder}
      placeholder={placeholder} {...field}/>
}
