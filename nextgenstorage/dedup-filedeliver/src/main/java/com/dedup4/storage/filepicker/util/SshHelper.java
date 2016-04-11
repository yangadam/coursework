package com.dedup4.storage.filepicker.util;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.client.subsystem.sftp.SftpClient;
import org.apache.sshd.client.subsystem.sftp.SftpClient.CloseableHandle;
import org.apache.sshd.common.util.io.NoCloseOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

/**
 * @author Yang Mengmeng Created on Mar 20, 2016
 */
public class SshHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SshHelper.class);

    private SshClient client = SshClient.setUpDefaultClient();
    private String username;
    private String host;
    private int port;
    private String password;

    /**
     * Default port:22, default username:root
     *
     * @param host     host
     * @param password password
     */
    public SshHelper(String host, String password) {
        this(host, "root", password);
    }

    /**
     * Default port:22
     *
     * @param host     host
     * @param username username
     * @param password password
     */
    public SshHelper(String host, String username, String password) {
        this(host, 22, username, password);
    }

    /**
     * @param host     host
     * @param port     port
     * @param username username
     * @param password password
     */
    public SshHelper(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * Execute command on remote server
     *
     * @param command command
     * @return true if execute command successfully
     */
    public boolean executeCommand(String command) {
        client.start();
        try (ClientSession session = createClientSession();
             ChannelExec channel = createChannelExec(session, command)) {
            channel.open().verify(10L, TimeUnit.SECONDS);
            return true;
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * Upload file to remote server
     *
     * @param clientFolder client folder
     * @param remoteFolder remote folder
     * @param fileName     file name
     * @return true if upload successfully
     */
    public boolean upload(Path clientFolder, String remoteFolder, String fileName) {
        client.start();
        try (ClientSession session = createClientSession()) {
            SftpClient sftpClient = session.createSftpClient();
            try (CloseableHandle handle = sftpClient.open(remoteFolder + "/" + fileName,
                    EnumSet.of(SftpClient.OpenMode.Write, SftpClient.OpenMode.Create));
                 InputStream in = Files.newInputStream(clientFolder.resolve(fileName))) {
                byte[] bytes = StreamUtils.copyToByteArray(in);
                sftpClient.write(handle, 0, bytes, 0, bytes.length);
                return true;
            } catch (IOException e) {
                LOGGER.error("", e);
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.error("", e);
        }
        return false;
    }

    private ClientSession createClientSession() throws IOException,
            InterruptedException {
        ClientSession session = client
                .connect(username, host, port)
                .verify(7L, TimeUnit.SECONDS).getSession();
        try {
            session.addPasswordIdentity(password);
            session.auth().verify(5L, TimeUnit.SECONDS);
            ClientSession returnValue = session;
            session = null;
            return returnValue;
        } finally {
            if (session != null) {
                session.close(false);
            }
        }
    }

    private ChannelExec createChannelExec(ClientSession session, String command)
            throws IOException {
        ChannelExec channel = session.createExecChannel(command);
        try {
            OutputStream stdout = new NoCloseOutputStream(System.out);
            OutputStream stderr = new NoCloseOutputStream(System.err);
            channel.setOut(stdout);
            channel.setErr(stderr);
            ChannelExec returnValue = channel;
            channel = null;
            return returnValue;
        } finally {
            if (channel != null) {
                channel.close(false);
            }
        }
    }

}