export const isDuplicate = (msg) => msg.content === "";

export const getCurrentTime = () => {
  const now = new Date();
  return {
    hour: now.getHours().toString().padStart(2, "0"),
    minutes: now.getMinutes().toString().padStart(2, "0")
  };
};
