import React, { Component } from 'react';


interface ButtonProps {
  text: string,
  onClick: () => void
}

interface GetButtonProps{
  text: string,
  url: string
}

export default class Button extends Component<ButtonProps, {}> {

  render = () => {
    return (
      <React.Fragment>
        <button onClick={this.props.onClick} >{this.props.text}</button>
      </React.Fragment>
    )
  }
}

export class GetButton extends Component<GetButtonProps> {

  getData = () => {
    console.log(this.props.url)
    fetch(this.props.url)
  }

  render = () => {
    return (
      <Button text={this.props.text} onClick={this.getData}/>
    )
  }
}
