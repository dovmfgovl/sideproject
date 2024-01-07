import React from "react";
import { Navbar } from "react-bootstrap";

const Bottom = () => {
  return (
    <>
      <Navbar
        className="navbar navbar-expand-sm bg-light justify-content-center"
        bg="dark"
        style={{ color: "white" }}
      >
        리액트캠프 Copyright &copy; 2023
      </Navbar>
    </>
  );
};

export default Bottom;
