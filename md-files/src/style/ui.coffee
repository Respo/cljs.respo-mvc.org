
hsl = require 'hsl'

exports.merge = merge = (args...) ->
  Object.assign {}, args...

exports.global =
  lineHeight: 2
  fontSize: '14px'
  fontFamily: "Verdana,'Hiragino Sans GB','WenQuanYi Micro Hei','Microsoft Yahei',sans-serif"

exports.fullscreen =
  top: 0
  width: "100%"
  position: "absolute"
  height: "100%"
  left: 0

exports.card =
  padding: "16px"

exports.button =
  lineHeight: 2.2
  minWidth: "64px"
  color: (hsl 0, 0, 100)
  textAlign: "center"
  backgroundColor: (hsl 200, 80, 60)
  cursor: "pointer"
  padding: "0 8px"
  display: "inline-block"

exports.input = merge exports.global,
  lineHeight: 2
  fontSize: "14px"
  backgroundColor: (hsl 0, 0, 96)
  width: "auto"
  padding: "0 8px"
  outline: "none"
  border: "none"

exports.flex =
  flex: 1

exports.center =
  alignItems: "center"
  justifyContent: "center"
  display: "flex"
  flexDirection: "column"

exports.row =
  alignItems: "stretch"
  display: "flex"
  flexDirection: "row"

exports.rowCenter =
  alignItems: "center"
  display: "flex"
  flexDirection: "row"

exports.column =
  alignItems: "stretch"
  display: "flex"
  flexDirection: "column"

exports.columnCenter =
  alignItems: "center"
  display: "flex"
  flexDirection: "column"
