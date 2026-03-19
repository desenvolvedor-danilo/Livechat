export const doFilter = (participant, currentUser) => {
  const participante = participant.filter(part => part !== currentUser)
  return participante
}
